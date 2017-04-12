package com.strider.domain;

import java.util.Date;
import java.util.List;
public class Order {

	private String id; // 订单编号
	private double money; // 订单总价
	private String receiverAddress; // 送货地址
	private String receiverName; // 收货人姓名
	private String receiverPhone; // 收货人电话
	private int paystate; // 订单状态
	private Date ordertime; // 下单时间
	private User user;
	private List<OrderItem> orderItems;//表示一个order对象，对应多个orderitem
	
	
}
