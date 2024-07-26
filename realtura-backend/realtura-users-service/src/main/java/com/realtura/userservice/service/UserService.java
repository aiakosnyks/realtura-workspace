package com.realtura.userservice.service;

import com.realtura.userservice.converter.UserConverter;
import com.realtura.userservice.dto.request.UserLoginRequest;
import com.realtura.userservice.dto.request.UserSaveRequest;
import com.realtura.userservice.dto.response.CreateResponse;
import com.realtura.userservice.dto.response.GenericResponse;
import com.realtura.userservice.exception.ExceptionMessages;
import com.realtura.userservice.exception.RealturaException;
import com.realtura.userservice.model.User;
import com.realtura.userservice.repository.UserRepository;
import com.realtura.userservice.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public GenericResponse<CreateResponse> save(UserSaveRequest request) {
        Optional<User> foundUser = userRepository.findByEmail(request.getEmail());

        if (foundUser.isPresent()) {
            log.error((ExceptionMessages.EMAIL_ALREADY_EXIST));
            throw new RuntimeException(ExceptionMessages.EMAIL_ALREADY_EXIST);
        }

        User user = UserConverter.toUser(request);
        User created = userRepository.save(user);
        log.info("user created. {}", user.getEmail());
        return GenericResponse.success(new CreateResponse(created.getId()));
    }

    public List<User> getUserList() {
        log.info("user listed. ");
        return userRepository.findAll();
    }

    /*
    //@Cacheable(value = "products", cacheNames = "products")
    @Transactional(readOnly = true)
    public Set<ProductResponse> getAll(ProductSearchRequest request) {

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (request.getSort() != null && request.getSort().equalsIgnoreCase("DESC")) {
            sortDirection = Sort.Direction.DESC;
        }

        Specification<Product> productSpecification = ProductSpecification.initProductSpecification(request);

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), Sort.by(sortDirection, "amount"));

        Page<Product> products = productRepository.findAll(productSpecification, pageRequest);

        log.info("db'den getirildi. product size:{}", products.getSize());

        return ProductConverter.toResponse(products.stream().toList());
    }
     */

    public void saveAsList(List<UserSaveRequest> request) {

        request.forEach(item -> save(item));
    }

    public Optional<User> getById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty()) {
            log.error(ExceptionMessages.USER_NOT_FOUND);
            throw new RuntimeException(ExceptionMessages.USER_NOT_FOUND);
        }
        return foundUser;
    }

    public Optional<User> getByEmail(String email) {

        Optional<User> foundUser = userRepository.findByEmail(email);

        if (!foundUser.get().getIsActive()) {
            log.error(ExceptionMessages.USER_NOT_FOUND);
            throw new RealturaException(ExceptionMessages.USER_NOT_FOUND);
        }
        log.info("customer found. {}", email);
        return foundUser;
    }

    public GenericResponse<?> login(UserLoginRequest request)
    {
        Optional<User> foundUser = userRepository.findByEmail(request.getEmail());

        if (foundUser.isEmpty()) {
            log.error(ExceptionMessages.USER_NOT_FOUND);
            throw new RealturaException(ExceptionMessages.USER_NOT_FOUND);
        }

        if (!request.getPassword().equals(foundUser.get().getPassword())) {
            log.error(ExceptionMessages.PASSWORD_INCORRECT);
            throw new RealturaException(ExceptionMessages.PASSWORD_INCORRECT);
        }

        if (!foundUser.get().getIsActive()) {
            log.error(ExceptionMessages.USER_NOT_ACTIVE);
            throw new RealturaException(ExceptionMessages.USER_NOT_ACTIVE);
        }
        log.info("customer found. {}", request.getEmail());
        return GenericResponse.success(UserConverter.toResponse(foundUser.get()));

    }
}
