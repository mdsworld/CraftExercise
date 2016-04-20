package com.craftexercise.interfac;

import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.craftexercise.entity.Order;
import com.craftexercise.service.DemoService;

@RestController
@RequestMapping(value = "/api/pizzastore")
public class DemoController
{
    private Logger logger = LoggerFactory.getLogger(DemoController.class);
    
    @Autowired
    DemoService service;
    
    //*********************************************************************************
    //*****************    Below services are for user/customer      ******************
    //*********************************************************************************


    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_USER'))")
    @RequestMapping(path ="/orders",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CatalogResponse getCatalogItems() throws Exception {
        logger.debug("invoked getCatalog API");
        CatalogResponse resp = DemoConverter.convertEntityToResponse(service.getCatalogItems());
        return resp;
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_USER'))")
    @RequestMapping(path ="/orders/showTotalAmt", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PlaceOrderResponse showTotalAmt(@Valid @RequestBody PlaceOrderRequest request) throws Exception {
        PlaceOrderResponse resp = new PlaceOrderResponse();
        BigDecimal totalAmt = service.calculateTotalAmt(request.getItemIds(), request.getPromoCode());
        resp.setTotalPrice(totalAmt);
        return resp;
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_USER'))")
    @RequestMapping(path ="/orders/confirmOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ConfirmOrderResponse confirmOrder(@Valid @RequestBody ConfirmOrderRequest request) throws Exception {
        Order order  = service.placeOrder(request.getItemIds(), request.getPromoCode());
        ConfirmOrderResponse resp = DemoConverter.convertEntityToResponse(order);
        if(resp == null)
            throw new Exception("Request failed.");
        return  resp;
    }

    
    //****************************************************************************
    //*********************    Below services are for admin      ******************
    //****************************************************************************

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/inventories",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public InventoryListResponse getAllInventoryItems() {
        InventoryListResponse resp = DemoConverter.conertEntityToResponse(service.getAllInventoryItems());
        return resp;
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/inventories/{itemId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public InventoryListResponse getInventoryItem(@PathVariable @Range(min = 1, message = "'itemId' must be a positive number") Integer itemId) {
        InventoryListResponse resp = DemoConverter.conertEntityToResponse(service.getItem(itemId));
        return resp;
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @RequestMapping(path ="/inventories/addItem", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DemoResponse addInventoryItem(@Valid @RequestBody AddItemRequest request) {
        com.craftexercise.entity.Item item = DemoConverter.conertRequestToEntity(request);
        return new DemoResponse(service.addItem(item));
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @RequestMapping(path ="/inventories/updateItem", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DemoResponse updateInventoryItem(@Valid @RequestBody UpdateItemRequest request) {
        com.craftexercise.entity.Item item = DemoConverter.convertRequestToEntity(request);
        return new DemoResponse(service.updateItem(item));
    }


    //*****************************************************************************
    //*********************    Below services are for admin      ******************
    //*****************************************************************************

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @RequestMapping(path ="/promos/{promocode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PromoResponse getPromo(@PathVariable @NotNull(message = "'promocode' is required")String promocode) throws Exception {
        return DemoConverter.convertEntityToResponse(service.getPromoByCode(promocode));
    }
    
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @RequestMapping(path ="/promos/addPromo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DemoResponse addPromo(@Valid @RequestBody AddPromoRequest request) {
        com.craftexercise.entity.Promo promo = DemoConverter.convertRequestToEntity(request);
        return new DemoResponse(service.addPromo(promo));
    }

}
