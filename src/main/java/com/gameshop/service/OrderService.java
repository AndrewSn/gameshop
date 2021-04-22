package com.gameshop.service;

import com.gameshop.Controller.PromoCodeController;
import com.gameshop.Enum.PromoStatus;
import com.gameshop.dto.GoodsDto;
import com.gameshop.dto.OrderDto;
import com.gameshop.entity.*;
import com.gameshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OrderService {

    Pattern pattern = Pattern.compile("^\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$");
    Matcher matcher;
    @Autowired
    GoodsRepo goodsRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    UserInfoRepo userInfoRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PromoCodeRepo promoCodeRepo;
    @Autowired
    PromoCodeController promoCodeController;


    public String processOrder(OrderDto orderDto) throws Exception {
        Optional<Goods> goods = findGoodsById(orderDto.getGoodsDto().get(0).getId());
        //validateUser(orderDto.getName(), orderDto.getSurname(), orderDto.getCity(), orderDto.getPhone(), orderDto.getBranchNumber(), orderDto.getPaymentMethod());
        processOrderWithDiscount(orderDto);
        // Order order = toOrderEntity(orderDto);
        //orderRepo.save(order);

        //UserInfo userInfo = toInfoEntity(orderDto);
        //userInfoRepo.save(userInfo);

        //order.setTotalAmount(orderDto.getTotalAmount());
        //order.setSaleAmount(orderDto.getSaleAmount());
        //order.setPaymentMethod(orderDto.getPaymentMethod());


        List<Goods> goodsList = toGoodsEntity(orderDto);
       /* for (int i = 0; i < goodsList.size(); i++) {
            goodsRepo.save(goodsList.get(i));
        }*/
        goodsRepo.saveAll(goodsList);


        return "";
    }


    public Optional<Goods> findGoodsById(long id) {
        return goodsRepo.findById(id);
    }

    public String validateUser(String name, String surname, String city, String phone, String branchNumber, String deliveryMethod) throws Exception {
        if (name.isEmpty() || name == null) {
            throw new Exception("Name not valid");
        }
        if (surname.isEmpty() || surname == null) {
            throw new Exception("Surname not valid");
        }
        if (city.isEmpty() || city == null) {
            throw new Exception("City not valid");
        }
        if (phone.isEmpty() || phone == null || phone.length() != 13 || pattern.matcher(phone).matches()) {
            throw new Exception("Phone not valid");
        }
        if (branchNumber.isEmpty() || branchNumber == null) {
            throw new Exception("Branch not valid");
        }
        if (deliveryMethod.isEmpty() || deliveryMethod == null) {
            throw new Exception("Delivery method not valid");
        }
        return "";
    }

    public String validateIfProductExist(long productId) throws Exception {
        if (!findGoodsById(productId).isPresent()) {
            throw new Exception("Product not exist");
        }
        return "";
    }

    public String validateIfPriceIfTrue(Optional<Goods> goods, Double price) throws Exception {
        if (goods.isPresent() && goods.get().getPriceOfGoods() == price) {
            throw new Exception("Price not true");
        }
        return "";
    }

    public String validateIfSalePriceIsTrue(Optional<Goods> goods, Double salePrice) throws Exception {
        if (goods.isPresent() && goods.get().getSalePriceOfGoods() == salePrice) {
            throw new Exception("Sale price is not true");
        }
        return "";
    }

    public Order toOrderEntity(OrderDto dto) {
        Order entity = new Order();
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setSaleAmount(dto.getSaleAmount());
        entity.setPaymentMethod(dto.getPaymentMethod());
        return entity;
    }

    public UserInfo toInfoEntity(OrderDto dto) {
        UserInfo infoEntity = new UserInfo();
        infoEntity.setName(dto.getName());
        infoEntity.setLastname(dto.getSurname());
        infoEntity.setPhoneNumber(dto.getPhone());
        infoEntity.setCity(dto.getCity());
        infoEntity.setBranchNumber(dto.getBranchNumber());
        return infoEntity;
    }

    public List<Goods> toGoodsEntity(OrderDto orderDto) {
        List<Goods> goodsList = new ArrayList<>();
        List<GoodsDto> goods1 = orderDto.getGoodsDto();
        int len = goods1.size();
        for (int i = 0; i < len; i++) {
            Goods goods = new Goods();
            goods.setBrandOfGoods(goods1.get(i).getBrand());
            goods.setPriceOfGoods(goods1.get(i).getPrice());
            goods.setDescriptionOfGoods(goods1.get(i).getDescription());
            goods.setSalePriceOfGoods(goods1.get(i).getSalePrice());
            goods.setNumberOfGoods(goods1.get(i).getNumberGoods());
            goodsList.add(goods);
        }
        return goodsList;
    }

    public List<Order> getOrdersByUserId(Long id) {
        return orderRepo.getOrderByUserId(id);
    }

    public Double getPersonalDiscount(List<Order> orders) {
        return Math.floor(orders.stream().mapToDouble(Order::getSaleAmount).sum() / 1000);
    }

    public void processOrderWithDiscount(OrderDto orderDto) throws NullPointerException {
        Order order = new Order();
        User user = new User();
        Long userId = orderDto.getUserId();
        UserInfo userInfo = new UserInfo();
        Double personaSale = userRepo.findById(userId).get().getPersonalDiscountOfUser();
        List<Order> orderList = getOrdersByUserId(userId);
        PromoCode promoCode = promoCodeRepo.getPromoCodeIfExist(orderDto.getPromoCode());

        if (userId != 0) {
            order.setPromoCode(promoCodeRepo.getPromoByPromoId(promoCode.getPromoId()));
            order.setTotalAmount(orderDto.getSaleAmount());
            if (promoCode != null && promoCode.getPromoStatus().equals(PromoStatus.active)) {
                String unit = promoCode.getPromoUnit().toString();
                switch (unit) {
                    case "currency":
                        order.setSaleAmount(orderDto.getSaleAmount() - ((orderDto.getSaleAmount() * personaSale) / 100) - promoCode.getPromoValue());
                        break;
                    case "percent":
                        order.setSaleAmount(orderDto.getSaleAmount() - ((orderDto.getSaleAmount() * personaSale) / 100) - ((orderDto.getSaleAmount() * promoCode.getPromoValue() / 100)));
                        break;
                }
                promoCodeRepo.updatePromoCodeStatus(PromoStatus.terminated, promoCode.getPromoId());
            } else {
                order.setSaleAmount(orderDto.getSaleAmount() - ((orderDto.getSaleAmount() * personaSale) / 100));
            }
            order.setPaymentMethod(orderDto.getPaymentMethod());
            order.setPersonalDiscount(personaSale);
            orderRepo.save(order);
            userInfo.setName(orderDto.getName());
            userInfo.setLastname(orderDto.getSurname());
            userInfo.setPhoneNumber(orderDto.getPhone());
            userInfo.setCity(orderDto.getCity());
            userInfo.setBranchNumber(orderDto.getBranchNumber());
            userInfo.setDeliveryMethod(orderDto.getPaymentMethod());
            userInfo.setUser(userRepo.findUserById(userId));
            userInfoRepo.save(userInfo);
            userRepo.updateUserPersonalDiscount(getPersonalDiscount(orderList), userId);
        } else {
            order.setTotalAmount(orderDto.getSaleAmount());
            order.setSaleAmount(0.0);
            order.setPaymentMethod(orderDto.getPaymentMethod());
            userInfo.setName(orderDto.getName());
            userInfo.setLastname(orderDto.getSurname());
            userInfo.setPhoneNumber(orderDto.getPhone());
            userInfo.setCity(orderDto.getCity());
            userInfo.setBranchNumber(orderDto.getBranchNumber());
            userInfo.setDeliveryMethod(orderDto.getPaymentMethod());
            orderRepo.save(order);
            userInfoRepo.save(userInfo);
        }
    }

//public void getPromoCodeSale(OrderDto orderDto){
//    PromoCode promoCode = promoCodeRepo.getPromoCodeIfExist(orderDto.getPromoCode());
//    double sum = 0.0;
//    Long userId = orderDto.getUserId();
//    Double personaSale = userRepo.findById(userId).get().getPersonalDiscount();
//    String unit  =  promoCode.getPromoUnit().toString();
//    switch (unit){
//                case "currency": sum =  (orderDto.getSaleAmount()-((orderDto.getSaleAmount()*personaSale)/100)-promoCode.getPromoValue());
//                break;
//                case "percent" : sum = (orderDto.getSaleAmount()-((orderDto.getSaleAmount()*personaSale)/100)-((orderDto.getSaleAmount()*promoCode.getPromoValue()/100)));
//                break;
//            }
//           promoCodeRepo.updatePromoCodeStatus(PromoCode.Status.terminated.toString(),promoCode.getPromoId());
//    }
}


