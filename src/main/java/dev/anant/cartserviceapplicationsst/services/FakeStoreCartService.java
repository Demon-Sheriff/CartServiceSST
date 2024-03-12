package dev.anant.cartserviceapplicationsst.services;

import dev.anant.cartserviceapplicationsst.dtos.FakeStoreCartDTO;
import dev.anant.cartserviceapplicationsst.models.Cart;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreCartService")
public class FakeStoreCartService implements CartService{

    private RestTemplate restTemplate = new RestTemplate();

    private Cart convertFakeStoreCartDTOtoCart(FakeStoreCartDTO fakeStoreCartDTO){
        Cart cart = new Cart();
        cart.setId(fakeStoreCartDTO.getId());
        cart.setUserId(fakeStoreCartDTO.getUserId());
        cart.setDate(fakeStoreCartDTO.getDate());
        cart.setProducts(fakeStoreCartDTO.getProducts());
        return cart;
    }

    private FakeStoreCartDTO convertCartToFakeStoreCartDTO(Cart cart){
        FakeStoreCartDTO fakeStoreCartDTO = new FakeStoreCartDTO();
        fakeStoreCartDTO.setId(cart.getId());
        fakeStoreCartDTO.setUserId(cart.getUserId());
        fakeStoreCartDTO.setDate(cart.getDate());
        fakeStoreCartDTO.setProducts(cart.getProducts());
        return fakeStoreCartDTO;
    }
    @Override
    public List<Cart> getAllCarts() {
        FakeStoreCartDTO[] fakeStoreCartDTOs = restTemplate.getForObject("https://fakestoreapi.com/carts"
                ,FakeStoreCartDTO[].class);

        if(fakeStoreCartDTOs != null){
            List<Cart> allCarts = new ArrayList<>();

            for(FakeStoreCartDTO fakeStoreCartDTO : fakeStoreCartDTOs){

                Cart currentCart = convertFakeStoreCartDTOtoCart(fakeStoreCartDTO);
                allCarts.add(currentCart);

            }

            return allCarts;
        }
        return null;
    }

    @Override
    public Cart getSingleCart(Long id) {

        FakeStoreCartDTO fakeStoreCartDTO = restTemplate.getForObject("https://fakestoreapi.com/carts/"+id
                ,FakeStoreCartDTO.class);
        if(fakeStoreCartDTO != null){
            return convertFakeStoreCartDTOtoCart(fakeStoreCartDTO);
        }

        return null;
    }

    @Override
    public List<Cart> getCartsByUserId(Long userId) {
        FakeStoreCartDTO[] fakeStoreCartDTOs = restTemplate.getForObject("https://fakestoreapi.com/carts/user/"+userId
                ,FakeStoreCartDTO[].class);

        if(fakeStoreCartDTOs != null){
            List<Cart> allCartsOfUser = new ArrayList<>();

            for(FakeStoreCartDTO fakeStoreCartDTO : fakeStoreCartDTOs){

                Cart currentCart = convertFakeStoreCartDTOtoCart(fakeStoreCartDTO);
                allCartsOfUser.add(currentCart);

            }

            return allCartsOfUser;
        }
        return null;
    }

    @Override
    public List<Cart> getCartsByDateRange(String startDate, String endDate) {

        FakeStoreCartDTO[] fakeStoreCartDTOs = restTemplate.getForObject("https://fakestoreapi.com/carts?startdate="+ startDate + "&endDate=" + endDate
                ,FakeStoreCartDTO[].class);

        if(fakeStoreCartDTOs != null){
            List<Cart> allCarts = new ArrayList<>();

            for(FakeStoreCartDTO fakeStoreCartDTO : fakeStoreCartDTOs){

                Cart currentCart = convertFakeStoreCartDTOtoCart(fakeStoreCartDTO);
                allCarts.add(currentCart);

            }

            return allCarts;
        }
        return null;
    }

    @Override
    public Cart createCart(Cart cart) {
        FakeStoreCartDTO fakeStoreCartDTO = convertCartToFakeStoreCartDTO(cart);
        FakeStoreCartDTO fakeStoreCartDTOOutput = restTemplate.postForObject(
                "https://fakestoreapi.com/carts",
                fakeStoreCartDTO,
                FakeStoreCartDTO.class
        );
        return convertFakeStoreCartDTOtoCart(fakeStoreCartDTOOutput);
    }

    @Override
    public void UpdateCart(Long id) {
        FakeStoreCartDTO fakeStoreCartDTO = convertCartToFakeStoreCartDTO(getSingleCart(id));
        restTemplate.put(
                "https://fakestoreapi.com/carts/" + id,
                fakeStoreCartDTO,
                FakeStoreCartDTO.class
        );
    }

    @Override
    public void deleteCart(Long id) {

        restTemplate.delete("https://fakestoreapi.com/carts/" + id);

    }
}
