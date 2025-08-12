package org.example.productsservice.grpc;

import io.grpc.stub.StreamObserver;
import org.example.productsservice.repository.IProductsRepository;
import org.springframework.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;

@GrpcService
public class ProductsProvider extends ProductServiceGrpc.ProductServiceImplBase {
  private final IProductsRepository repository;

  public ProductsProvider(IProductsRepository repository) {
    this.repository = repository;
  }

  @Override
  public void getProduct(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
    repository.findById(request.getProductId())
      .switchIfEmpty(Mono.error(new RuntimeException("Product not found")))
      .subscribe(product -> {
        ProductResponse response = ProductResponse
          .newBuilder()
          .setDescription(product.getDescription())
          .setId(product.getId())
          .setName(product.getName())
          .setPrice(product.getPrice())
          .setStock(product.getStock())
          .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
      }, responseObserver::onError).dispose();
  }
}
