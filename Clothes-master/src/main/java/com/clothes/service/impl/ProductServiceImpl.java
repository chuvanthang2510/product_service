package com.clothes.service.impl;

import com.clothes.domain.Constants;
import com.clothes.domain.dto.FilterDTO;
import com.clothes.domain.dto.ProductDTO;
import com.clothes.domain.entity.Brand;
import com.clothes.domain.entity.Category;
import com.clothes.domain.entity.Color;
import com.clothes.domain.entity.Image;
import com.clothes.domain.entity.Product;
import com.clothes.domain.entity.Size;
import com.clothes.repository.BrandRepository;
import com.clothes.repository.CategoryRepository;
import com.clothes.repository.ColorRepository;
import com.clothes.repository.ProductRepository;
import com.clothes.repository.SizeRepository;
import com.clothes.service.FileService;
import com.clothes.service.ImageService;
import com.clothes.service.ProductService;
import javassist.NotFoundException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private FileService fileService;

    @Override
    public Product findById(Long productId) throws NotFoundException {
        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product noy found"));
    }

    @Override
    public Product create(ProductDTO productDTO) throws NotFoundException, IOException {
        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
        if (!categoryOptional.isPresent()) {
            throw new NotFoundException("Category not found");
        }
        Optional<Brand> brandOptional = brandRepository.findById(productDTO.getBrandId());
        if (!brandOptional.isPresent()) {
            throw new NotFoundException("Brand not found");
        }
        List<Color> colors = new ArrayList<>();
        for (Long colorId : productDTO.getColorIds()) {
            Optional<Color> colorOptional = colorRepository.findById(colorId);
            if (!colorOptional.isPresent()) {
                throw new NotFoundException("Color not found");
            }
            colors.add(colorOptional.get());
        }
        List<Size> sizes = new ArrayList<>();
        for (Long sizeId : productDTO.getSizeIds()) {
            Optional<Size> sizeOptional = sizeRepository.findById(sizeId);
            if (!sizeOptional.isPresent()) {
                throw new NotFoundException("Size not found");
            }
            sizes.add(sizeOptional.get());
        }
        String rootDir = System.getProperty("user.dir");
        rootDir = rootDir.replace("\\", "/");
        Image avatar = new Image();
        if (productDTO.getUploadAvatar() != null) {
            avatar.setLink(fileService.saveFile(rootDir + Constants.DIR_UPLOAD,
                    productDTO.getUploadAvatar().getOriginalFilename(), productDTO.getUploadAvatar().getBytes()));
        }
        List<Image> images = new ArrayList<>();
        if (!CollectionUtils.isEmpty(productDTO.getUploadImages())) {
            for (MultipartFile multipartFile : productDTO.getUploadImages()) {
                String imageLink = fileService.saveFile(rootDir + Constants.DIR_UPLOAD,
                        multipartFile.getOriginalFilename(), multipartFile.getBytes());
                Image image = Image.builder().link(imageLink).build();
                images.add(image);
            }
        }
        Product product = Product.builder()
                .name(productDTO.getName())
                .shortDescription(productDTO.getShortDescription())
                .longDescription(productDTO.getLongDescription())
                .importPrice(productDTO.getImportPrice())
                .price(productDTO.getPrice())
                .quantity(productDTO.getQuantity())
                .category(categoryOptional.get())
                .brand(brandOptional.get())
                .avatar(avatar)
                .images(images)
                .colors(colors)
                .sizes(sizes)
                .createdDate(new Date())
                .status(Boolean.TRUE).build();
        return productRepository.save(product);
    }

    @Override
    public Product update(Long productId, ProductDTO productDTO) throws NotFoundException, IOException {
        Product product = this.findById(productId);
        if (productDTO.getCategoryId() != product.getCategory().getId()) {
            Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
            if (!categoryOptional.isPresent()) {
                throw new NotFoundException("Category not found");
            }
            product.setCategory(categoryOptional.get());
        }
        if (productDTO.getBrandId() != product.getBrand().getId()) {
            Optional<Brand> brandOptional = brandRepository.findById(productDTO.getBrandId());
            if (!brandOptional.isPresent()) {
                throw new NotFoundException("Brand not found");
            }
            product.setBrand(brandOptional.get());
        }
        String rootDir = System.getProperty("user.dir");
        rootDir = rootDir.replace("\\", "/");
        if (productDTO.getUploadAvatar() != null) {
            imageService.delete(product.getAvatar().getId());
            Image avatar = new Image();
            avatar.setLink(fileService.saveFile(rootDir + Constants.DIR_UPLOAD,
                    productDTO.getUploadAvatar().getOriginalFilename(), productDTO.getUploadAvatar().getBytes()));
        }
        if (!CollectionUtils.isEmpty(productDTO.getUploadImages())) {
            for (Image image : product.getImages()) {
                imageService.delete(image.getId());
            }
            List<Image> images = new ArrayList<>();
            for (MultipartFile multipartFile : productDTO.getUploadImages()) {
                String imageLink = fileService.saveFile(rootDir + Constants.DIR_UPLOAD,
                        multipartFile.getOriginalFilename(), multipartFile.getBytes());
                Image image = Image.builder().link(imageLink).build();
                images.add(image);
            }
        }
        List<Color> colors = new ArrayList<>();
        for (Long colorId : productDTO.getColorIds()) {
            Optional<Color> colorOptional = colorRepository.findById(colorId);
            if (!colorOptional.isPresent()) {
                throw new NotFoundException("Color not found");
            }
            colors.add(colorOptional.get());
        }
        List<Size> sizes = new ArrayList<>();
        for (Long sizeId : productDTO.getSizeIds()) {
            Optional<Size> sizeOptional = sizeRepository.findById(sizeId);
            if (!sizeOptional.isPresent()) {
                throw new NotFoundException("Size not found");
            }
            sizes.add(sizeOptional.get());
        }
        product.setName(productDTO.getName());
        product.setShortDescription(productDTO.getShortDescription());
        product.setLongDescription(productDTO.getLongDescription());
        if (productDTO.getImportPrice() != null) {
            product.setImportPrice(productDTO.getImportPrice());
        }
        if (productDTO.getPrice() != null) {
            product.setPrice(productDTO.getPrice());
        }
        if (productDTO.getQuantity() != null) {
            product.setQuantity(productDTO.getQuantity());
        }
        if (productDTO.getStatus() != null) {
            product.setStatus(productDTO.getStatus());
        }

        product.getColors().clear();
        product.getColors().addAll(colors);

        product.getSizes().clear();
        product.getSizes().addAll(sizes);

        product.setModifiedDate(new Date());
        return productRepository.save(product);
    }

    @Override
    public void delete(Long productId) throws NotFoundException {
        productRepository.delete(this.findById(productId));
    }

    @Override
    public List<Product> searchProduct(Integer page, Integer size, FilterDTO filterDTO) throws NotFoundException {
        List<Product> products = new ArrayList<>();
        if (StringUtils.isEmpty(filterDTO.getName())) {
            products = productRepository.findAll();
        } else {
            products = productRepository.getByName(filterDTO.getName());
        }
        if (filterDTO.getFromPrice() != null) {
            products = products.stream().filter(p -> p.getPrice() >= filterDTO.getFromPrice())
                    .collect(Collectors.toList());
        }
        if (filterDTO.getToPrice() != null) {
            products = products.stream().filter(p -> p.getPrice() <= filterDTO.getToPrice())
                    .collect(Collectors.toList());
        }
        if (!CollectionUtils.isEmpty(filterDTO.getColorIds())) {
            List<Product> filterColors = new ArrayList<>();
            for (Long colorId : filterDTO.getColorIds()) {
                Optional<Color> colorOptional = colorRepository.findById(colorId);
                if (!colorOptional.isPresent()) {
                    throw new NotFoundException("Color not found");
                }
                filterColors.addAll(products.stream().filter(p -> p.getColors().contains(colorOptional.get())).collect(
                        Collectors.toList()));
            }
            products = filterColors;
        }
        if (!CollectionUtils.isEmpty(filterDTO.getSizeIds())) {
            List<Product> filterSizes = new ArrayList<>();
            for (Long sizeId : filterDTO.getSizeIds()) {
                Optional<Size> sizeOptional = sizeRepository.findById(sizeId);
                if (!sizeOptional.isPresent()) {
                    throw new NotFoundException("Size not found");
                }
                filterSizes.addAll(products.stream().filter(p -> p.getSizes().contains(sizeOptional.get())).collect(
                        Collectors.toList()));
            }
            products = filterSizes;
        }
        PagedListHolder<Product> pagedListHolder = new PagedListHolder<>(products);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(size);
        return pagedListHolder.getPageList();
    }

    @Override
    public List<Product> getAllProduct(Integer page, Integer size) {
        PagedListHolder<Product> pagedListHolder = new PagedListHolder<>(productRepository.findAll());
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(size);
        return pagedListHolder.getPageList();
    }
}
