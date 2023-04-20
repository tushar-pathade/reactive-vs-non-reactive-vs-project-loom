package com.example.reactive;

import com.example.reactive.controller.ProductController;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class ReactiveApplicationTests {

//	@Autowired
//	private WebTestClient webTestClient;
//
//	@MockBean
//	private ProductService productService;
//
//	@Test
//	public void addProductTest(){
//		Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("102", "mobile", 10, 10000));
//		when(productService.saveProduct(productDtoMono)).thenReturn(productDtoMono);
//
//		webTestClient.post().uri("/products").body(Mono.just(productDtoMono), ProductDto.class)
//				.exchange()
//				.expectStatus().isOk(); // 200
//	}
//
//	@Test
//	public void getProductsTest(){
//		Flux<ProductDto> productDtoFlux = Flux.just(new ProductDto("233", "mobile", 3, 34404),
//				new ProductDto("34", "fdf", 4, 4455));
//
//		when(productService.getProducts()).thenReturn(productDtoFlux);
//
//		Flux<ProductDto> responseBody = webTestClient.get().uri("/products")
//				.exchange()
//				.expectStatus().isOk()
//				.returnResult(ProductDto.class)
//				.getResponseBody();
//
//		StepVerifier.create(responseBody)
//				.expectSubscription()
//				.expectNext(new ProductDto("233", "mobile", 3, 34404))
//				.expectNext(new ProductDto("34", "fdf", 4, 4455))
//				.verifyComplete();
//	}

}
