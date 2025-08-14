package org.example.cartservice.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.cartservice.dto.GetProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProductConsumer {
//  @Value("${products.grpc.host}")
//  private  String grpcHost;
//  @Value("${products.grpc.port}")
//  private   Integer grpcPort;
  private ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
  private ProductServiceGrpc.ProductServiceBlockingStub stub = ProductServiceGrpc.newBlockingStub(channel);

  public Mono<GetProductDTO> getProduct(Long productId) {
    ProductRequest request = ProductRequest.newBuilder().setProductId(productId).build();
    ProductResponse response = stub.getProduct(request);
    GetProductDTO dto = new GetProductDTO(response.getId(), response.getName(), response.getDescription(), response.getStock(), response.getPrice());
    return Mono.just(dto);
  }
}
