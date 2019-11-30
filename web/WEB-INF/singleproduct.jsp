<%-- 
    Document   : singleproduct
    Created on : Nov 25, 2019, 9:01:44 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-wrap hero-bread" style="background-image: url('assets/vegefoods/images/bg_1.jpg');">
      <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
          <div class="col-md-9 ftco-animate text-center">
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home</a></span> <span class="mr-2"><a href="index.html">Product</a></span> <span>Product Single</span></p>
            <h1 class="mb-0 bread">Product Single</h1>
          </div>
        </div>
      </div>
    </div>

    <section class="ftco-section">
    	<div class="container">
    		<div class="row">
                    <c:forEach var="product" items="${singleproducts}">
                        <c:if test="${product != null}">
    			<div class="col-lg-6 mb-5 ftco-animate">
    				<a href="assets/vegefoods/images/product-1.jpg" class="image-popup"><img src="assets/vegefoods/images/product-1.jpg" class="img-fluid" alt="Colorlib Template"></a>
    			</div>
    			<div class="col-lg-6 product-details pl-md-5 ftco-animate">
                            <h3 style="text-transform: capitalize">${product.name}</h3>    				
    				<p class="price"><span>Rs ${product.amount}</span></p>
    				<p>A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Text should turn around and return to its own, safe country. But nothing the copy said could convince her and so it didn’t take long until.
						</p>
                        <div class="row mt-4">
                            <div class="col-md-6">
                                <div class="form-group d-flex">
                                    <div class="select-wrap">
                                        <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                        <select name="" id="" class="form-control">
                                              <option value="">Small</option>
                                          <option value="">Medium</option>
                                          <option value="">Large</option>
                                          <option value="">Extra Large</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
        
                        <div class="input-group col-md-6 d-flex mb-3">
                            <span class="input-group-btn mr-2">
                                    <button type="button" class="quantity-left-minus btn"  data-type="minus" data-field="">
                               <i class="ion-ios-remove"></i>
                                    </button>
                                    </span>
                            <input type="text" id="quantity" name="quantity" class="form-control input-number" value="1" min="1" max="100">
                            <span class="input-group-btn ml-2">
                                    <button type="button" class="quantity-right-plus btn" data-type="plus" data-field="">
                                 <i class="ion-ios-add"></i>
                             </button>
                            </span>
	          	</div>
	          	<div class="w-100"></div>
	          	<div class="col-md-12">
	          		<p style="color: #000;">${product.available} kg available</p>
	          	</div>
                            </div>
                            <p><a href="cart.html" class="btn btn-black py-3 px-5">Add to Cart</a></p>
                        </div>
                        </c:if>
                    </c:forEach>
    		</div>
    	</div>
    </section>

    <section class="ftco-section">
    	<div class="container">
        <div class="row justify-content-center mb-3 pb-3">
          <div class="col-md-12 heading-section text-center ftco-animate">
          	<span class="subheading">Products</span>
            <h2 class="mb-4">Related Products</h2>
            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia</p>
          </div>
        </div>   		
    	</div>
    	<div class="container">
            <div class="row">
                <div class="col-md-6 col-lg-3 ftco-animate">
                    <div class="product">
                        <a href="#" class="img-prod"><img class="img-fluid" src="assets/vegefoods/images/product-1.jpg" alt="Colorlib Template">
                            <span class="status">30%</span>
                            <div class="overlay"></div>
                        </a>
                        <div class="text py-3 pb-4 px-3 text-center">
                            <h3><a href="#">Bell Pepper</a></h3>
                            <div class="d-flex">
                                <div class="pricing">
                                    <p class="price"><span class="mr-2 price-dc">$120.00</span><span class="price-sale">$80.00</span></p>
                                </div>
                            </div>
                            <div class="bottom-area d-flex px-3">
                                <div class="m-auto d-flex">
                                    <a href="#" class="add-to-cart d-flex justify-content-center align-items-center text-center">
                                            <span><i class="ion-ios-menu"></i></span>
                                    </a>
                                    <a href="#" class="buy-now d-flex justify-content-center align-items-center mx-1">
                                            <span><i class="ion-ios-cart"></i></span>
                                    </a>
                                    <a href="#" class="heart d-flex justify-content-center align-items-center ">
                                            <span><i class="ion-ios-heart"></i></span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
    		
            </div>
    	</div>
    </section>     