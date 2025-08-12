package org.example.cartservice.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {
  @Value("${products.grpc.host}")
  private  String grpcHost;
  @Value("${products.grpc.port}")
  private   Integer grpcPort;
  private ManagedChannel channel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort).usePlaintext().build();
  private ProductServiceGrpc.ProductServiceBlockingStub stub = ProductServiceGrpc.newBlockingStub(channel);

  public ProductResponse getProduct(Long productId) {
    ProductRequest request = ProductRequest.newBuilder().setProductId(productId).build();
    return stub.getProduct(request);
  }
}
