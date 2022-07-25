package com.savvycom.product_service.service.impl;

import com.savvycom.product_service.controller.BaseController;
import com.savvycom.product_service.domain.dto.product.create_product.CreateProductRequest;
import com.savvycom.product_service.domain.dto.product.get_product.ProductDTO;
import com.savvycom.product_service.domain.dto.product.product_review.ProductReviewRequest;
import com.savvycom.product_service.domain.entity.Brand;
import com.savvycom.product_service.domain.entity.Product;
import com.savvycom.product_service.domain.entity.Review;
import com.savvycom.product_service.domain.feign.User;
import com.savvycom.product_service.repository.*;
import com.savvycom.product_service.service.ProductService;
import com.savvycom.product_service.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseController implements ProductService {


    private final ProductRepository productRepository;

    private final ProductCustomRepository productCustomRepository;

    private final BrandRepository brandRepository;

    private final UserService userService;

    private final ReviewRepository reviewRepository;


    @Override
    public ResponseEntity<?> createProduct(CreateProductRequest createProductRequest) {
    	try {
            Brand brand = this.brandRepository.findById(createProductRequest.getBrandId()).orElseThrow(() -> new IllegalArgumentException());
            Product product = new Product();
            product.setName(createProductRequest.getName());
            product.setDescription(createProductRequest.getDescription());
            product.setPrice(createProductRequest.getPrice());
            product.setBrand(brand);
            product.setType(createProductRequest.getType());
            productRepository.save(product);
            return toSuccessResult(product, "Them san pham thanh cong");
		} catch (Exception e) {
	        return toExceptionResult("Loi them san pham", 500);
		}

    }

    @Override
    public ProductDTO getProduct(Long id) {
        ProductDTO productDTO = this.productCustomRepository.getProduct(id);
        return productDTO;
    }


    @Override
    public ResponseEntity<?> getAllProduct() {
        return toSuccessResult(this.productCustomRepository.getAllProduct(), "Lay toan bo san pham thanh cong");
    }

    @Override
    public ResponseEntity<?> getAllProductByPage(Integer pageNum, Integer pageSize) {
        return toSuccessResult(this.productCustomRepository.getAllProductByPage(pageNum,pageSize),"Lay toan bo san pham theo trang thanh cong");
    }



//    @Override
//    public Product findById(Long productId) throws NotFoundException {
//        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product noy found"));
//    }
//
//    @Override
//    public Product create(ProductDTO productDTO) throws NotFoundException, IOException {
//        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
//        if (!categoryOptional.isPresent()) {
//            throw new NotFoundException("Category not found");
//        }
//        Optional<Brand> brandOptional = brandRepository.findById(productDTO.getBrandId());
//        if (!brandOptional.isPresent()) {
//            throw new NotFoundException("Brand not found");
//        }
//        List<Color> colors = new ArrayList<>();
//        for (Long colorId : productDTO.getColorIds()) {
//            Optional<Color> colorOptional = colorRepository.findById(colorId);
//            if (!colorOptional.isPresent()) {
//                throw new NotFoundException("Color not found");
//            }
//            colors.add(colorOptional.get());
//        }
//        List<Size> sizes = new ArrayList<>();
//        for (Long sizeId : productDTO.getSizeIds()) {
//            Optional<Size> sizeOptional = sizeRepository.findById(sizeId);
//            if (!sizeOptional.isPresent()) {
//                throw new NotFoundException("Size not found");
//            }
//            sizes.add(sizeOptional.get());
//        }
//        String rootDir = System.getProperty("user.dir");
//        rootDir = rootDir.replace("\\", "/");
//        Image avatar = new Image();
//        if (productDTO.getUploadAvatar() != null) {
//            avatar.setLink(fileService.saveFile(rootDir + Constants.DIR_UPLOAD,
//                    productDTO.getUploadAvatar().getOriginalFilename(), productDTO.getUploadAvatar().getBytes()));
//        }
//        List<Image> images = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(productDTO.getUploadImages())) {
//            for (MultipartFile multipartFile : productDTO.getUploadImages()) {
//                String imageLink = fileService.saveFile(rootDir + Constants.DIR_UPLOAD,
//                        multipartFile.getOriginalFilename(), multipartFile.getBytes());
//                Image image = Image.builder().link(imageLink).build();
//                images.add(image);
//            }
//        }
//        Product product = Product.builder()
//                .name(productDTO.getName())
//                .shortDescription(productDTO.getShortDescription())
//                .longDescription(productDTO.getLongDescription())
//                .importPrice(productDTO.getImportPrice())
//                .price(productDTO.getPrice())
//                .quantity(productDTO.getQuantity())
//                .category(categoryOptional.get())
//                .brand(brandOptional.get())
//                .avatar(avatar)
//                .images(images)
//                .colors(colors)
//                .sizes(sizes)
//                .createdDate(new Date())
//                .status(Boolean.TRUE)
//                .numberOfRate(0)
//                .rate((double) 0).build();
//        return productRepository.save(product);
//    }
//
//    @Override
//    public Product update(Long productId, ProductDTO productDTO) throws NotFoundException, IOException {
//        Product product = this.findById(productId);
//        if (productDTO.getCategoryId() != product.getCategory().getId()) {
//            Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
//            if (!categoryOptional.isPresent()) {
//                throw new NotFoundException("Category not found");
//            }
//            product.setCategory(categoryOptional.get());
//        }
//        if (productDTO.getBrandId() != product.getBrand().getId()) {
//            Optional<Brand> brandOptional = brandRepository.findById(productDTO.getBrandId());
//            if (!brandOptional.isPresent()) {
//                throw new NotFoundException("Brand not found");
//            }
//            product.setBrand(brandOptional.get());
//        }
//        String rootDir = System.getProperty("user.dir");
//        rootDir = rootDir.replace("\\", "/");
//        if (productDTO.getUploadAvatar() != null) {
//            imageService.delete(product.getAvatar().getId());
//            Image avatar = new Image();
//            avatar.setLink(fileService.saveFile(rootDir + Constants.DIR_UPLOAD,
//                    productDTO.getUploadAvatar().getOriginalFilename(), productDTO.getUploadAvatar().getBytes()));
//        }
//        if (!CollectionUtils.isEmpty(productDTO.getUploadImages())) {
//            for (Image image : product.getImages()) {
//                imageService.delete(image.getId());
//            }
//            List<Image> images = new ArrayList<>();
//            for (MultipartFile multipartFile : productDTO.getUploadImages()) {
//                String imageLink = fileService.saveFile(rootDir + Constants.DIR_UPLOAD,
//                        multipartFile.getOriginalFilename(), multipartFile.getBytes());
//                Image image = Image.builder().link(imageLink).build();
//                images.add(image);
//            }
//        }
//        List<Color> colors = new ArrayList<>();
//        for (Long colorId : productDTO.getColorIds()) {
//            Optional<Color> colorOptional = colorRepository.findById(colorId);
//            if (!colorOptional.isPresent()) {
//                throw new NotFoundException("Color not found");
//            }
//            colors.add(colorOptional.get());
//        }
//        List<Size> sizes = new ArrayList<>();
//        for (Long sizeId : productDTO.getSizeIds()) {
//            Optional<Size> sizeOptional = sizeRepository.findById(sizeId);
//            if (!sizeOptional.isPresent()) {
//                throw new NotFoundException("Size not found");
//            }
//            sizes.add(sizeOptional.get());
//        }
//        product.setName(productDTO.getName());
//        product.setShortDescription(productDTO.getShortDescription());
//        product.setLongDescription(productDTO.getLongDescription());
//        if (productDTO.getImportPrice() != null) {
//            product.setImportPrice(productDTO.getImportPrice());
//        }
//        if (productDTO.getPrice() != null) {
//            product.setPrice(productDTO.getPrice());
//        }
//        if (productDTO.getQuantity() != null) {
//            product.setQuantity(productDTO.getQuantity());
//        }
//        if (productDTO.getStatus() != null) {
//            product.setStatus(productDTO.getStatus());
//        }
//
//        product.getColors().clear();
//        product.getColors().addAll(colors);
//
//        product.getSizes().clear();
//        product.getSizes().addAll(sizes);
//
//        product.setModifiedDate(new Date());
//        return productRepository.save(product);
//    }
//
//    @Override
//    public void delete(Long productId) throws NotFoundException {
//        productRepository.delete(this.findById(productId));
//    }
//
//    @Override
//    public List<Product> searchProduct(Integer page, Integer size, FilterDTO filterDTO) throws NotFoundException {
//        List<Product> products = new ArrayList<>();
//        if (StringUtils.isEmpty(filterDTO.getName())) {
//            products = productRepository.findAll();
//        } else {
//            products = productRepository.getByName(filterDTO.getName());
//        }
//        if (filterDTO.getFromPrice() != null) {
//            products = products.stream().filter(p -> p.getPrice() >= filterDTO.getFromPrice())
//                    .collect(Collectors.toList());
//        }
//        if (filterDTO.getToPrice() != null) {
//            products = products.stream().filter(p -> p.getPrice() <= filterDTO.getToPrice())
//                    .collect(Collectors.toList());
//        }
//        if (!CollectionUtils.isEmpty(filterDTO.getColorIds())) {
//            List<Product> filterColors = new ArrayList<>();
//            for (Long colorId : filterDTO.getColorIds()) {
//                Optional<Color> colorOptional = colorRepository.findById(colorId);
//                if (!colorOptional.isPresent()) {
//                    throw new NotFoundException("Color not found");
//                }
//                filterColors.addAll(products.stream().filter(p -> p.getColors().contains(colorOptional.get())).collect(
//                        Collectors.toList()));
//            }
//            products = filterColors;
//        }
//        if (!CollectionUtils.isEmpty(filterDTO.getSizeIds())) {
//            List<Product> filterSizes = new ArrayList<>();
//            for (Long sizeId : filterDTO.getSizeIds()) {
//                Optional<Size> sizeOptional = sizeRepository.findById(sizeId);
//                if (!sizeOptional.isPresent()) {
//                    throw new NotFoundException("Size not found");
//                }
//                filterSizes.addAll(products.stream().filter(p -> p.getSizes().contains(sizeOptional.get())).collect(
//                        Collectors.toList()));
//            }
//            products = filterSizes;
//        }
//        PagedListHolder<Product> pagedListHolder = new PagedListHolder<>(products);
//        pagedListHolder.setPage(page);
//        pagedListHolder.setPageSize(size);
//        return pagedListHolder.getPageList();
//    }
//
//    @Override
//    public List<Product> getAllProduct(Integer page, Integer size) {
//        PagedListHolder<Product> pagedListHolder = new PagedListHolder<>(productRepository.findAll());
//        pagedListHolder.setPage(page);
//        pagedListHolder.setPageSize(size);
//        return pagedListHolder.getPageList();
//    }
//
    @Override
    public Product productReview(ProductReviewRequest productReviewRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = this.userService.getUserByUsername(username).orElseThrow(() -> new IllegalArgumentException());
        Review review = new Review();
        review.setProductId(productReviewRequest.getProductId());
        review.setComment(productReviewRequest.getComment());
        review.setRate(productReviewRequest.getRate());
        review.setUsername(user.getName());
        reviewRepository.save(review);

        Product product = this.productRepository.findById(review.getProductId()).orElseThrow(() -> new IllegalArgumentException());
        List<Review> reviews = this.reviewRepository.findByProductId(product.getProductId());
        double rate = 0;
        for (Review review1: reviews
             ) {
            rate += review1.getRate();
        }
        rate = rate/reviews.size();

        product.setRate(rate);
        product.setNumberOfRate(reviews.size());
        productRepository.save(product);
        return product;
    }
}
// feign tra ra DTO