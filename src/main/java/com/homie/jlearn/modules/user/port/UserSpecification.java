package com.homie.jlearn.modules.user.port;

import com.homie.jlearn.modules.user.entity.UserEntity;
import com.homie.jlearn.modules.user.entity.UserEntity_;
import com.homie.jlearn.utils.SpecificationBuilder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<UserEntity> {

    private final UserCriteria userCriteria;

    public UserSpecification(UserCriteria userCriteria) {
        this.userCriteria = userCriteria;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return reportCompositeSpecification(userCriteria).toPredicate(root, query, builder);
    }

    public static Specification<UserEntity> reportCompositeSpecification(UserCriteria userCriteria) {
        return combine(getSpecifications(userCriteria));
    }

    private static <T> Specification<T> combine(Collection<Specification<T>> specifications) {
        Specification<T> combinedSpecification = Specification.where(null);
        for (Specification<T> specification : specifications) {
            if (combinedSpecification == null) {
                combinedSpecification = Specification.where(specification);
            } else {
                combinedSpecification = combinedSpecification.and(specification);
            }
        }
        return combinedSpecification;
    }

    public static List<Specification<UserEntity>> getSpecifications(UserCriteria userCriteria) {
        if (userCriteria == null) {
            return Collections.emptyList();
        }
        List<Specification<UserEntity>> specifications = new ArrayList<>();
        if (userCriteria.getId() != null) {
            specifications.add(SpecificationBuilder.buildSpecification(UserEntity_.id, userCriteria.getId()));
        }
        if (userCriteria.getUsername() != null) {
            specifications.add(SpecificationBuilder.buildSpecification(UserEntity_.username, userCriteria.getUsername()));
        }
        if (userCriteria.getFirstName() != null) {
            specifications.add(SpecificationBuilder.buildSpecification(UserEntity_.firstName, userCriteria.getFirstName()));
        }
        if (userCriteria.getLastName() != null) {
            specifications.add(SpecificationBuilder.buildSpecification(UserEntity_.lastName, userCriteria.getLastName()));
        }
        if (userCriteria.getEmail() != null) {
            specifications.add(SpecificationBuilder.buildSpecification(UserEntity_.email, userCriteria.getEmail()));
        }
        if (userCriteria.getStatus() != null) {
            specifications.add(SpecificationBuilder.buildSpecification(UserEntity_.status, userCriteria.getStatus()));
        }
        if (userCriteria.getRole() != null) {
            specifications.add(SpecificationBuilder.buildSpecification(UserEntity_.role, userCriteria.getRole()));
        }
        if (userCriteria.getActivationKey() != null) {
            specifications.add(SpecificationBuilder.buildSpecification(UserEntity_.activationKey, userCriteria.getActivationKey()));
        }
        return specifications;
    }
}
