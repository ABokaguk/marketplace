package com.marketplace.catalogservice.repository;

import com.marketplace.catalogservice.dto.CatalogSearchCriteria;
import com.marketplace.catalogservice.entity.Catalog;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import static org.springframework.data.jpa.domain.Specification.where;

@Repository
@AllArgsConstructor
public class CatalogSpecificationRepositoryImpl implements CatalogSpecificationRepository {

    /**
     * Метод для получения списка выбранных фильтров
     */
    @Override
    public Specification<Catalog> getFilter(CatalogSearchCriteria catalogSearchCriteria) {
        return (catalogRoot, query, criteriaBuilder) -> {
            query.distinct(true);
            return where(filterByMinPrice(catalogSearchCriteria.getMinPrice())
                    .and(filterByMaxPrice(catalogSearchCriteria.getMinPrice()))
                    .and(filterByGender(catalogSearchCriteria.getGender()))
                    .and(filterByCategory(catalogSearchCriteria.getCategory()))
                    .and(filterByIsDiscounted(catalogSearchCriteria.getIsDiscounted()))
            )
                    .toPredicate(catalogRoot, query, criteriaBuilder);
        };
    }

    @Override
    public Specification<Catalog> filterByMinPrice(Long minPrice) {
        return ((catalogRoot, query, criteriaBuilder) -> {
            if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(catalogRoot.get("price"), minPrice);
            }
            return criteriaBuilder.conjunction();
        });
    }

    @Override
    public Specification<Catalog> filterByMaxPrice(Long maxPrice) {
        return ((catalogRoot, query, criteriaBuilder) -> {
            if (maxPrice != null) {
                return criteriaBuilder.lessThanOrEqualTo(catalogRoot.get("price"), maxPrice);
            }
            return criteriaBuilder.conjunction();
        });
    }

    @Override
    public Specification<Catalog> filterByGender(Long gender) {
        return ((catalogRoot, query, criteriaBuilder) -> {
            if (gender != null) {
                return criteriaBuilder.equal(catalogRoot.get("gender"), gender);
            }
            return criteriaBuilder.conjunction();
        });
    }

    @Override
    public Specification<Catalog> filterByCategory(Long category) {
        return ((catalogRoot, query, criteriaBuilder) -> {
            if (category != null) {
                return criteriaBuilder.equal(catalogRoot.get("category"), category);
            }
            return criteriaBuilder.conjunction();
        });
    }

    @Override
    public Specification<Catalog> filterByIsDiscounted(Boolean isDiscounted) {
        return ((catalogRoot, query, criteriaBuilder) -> {
            if (isDiscounted != null) {
                return criteriaBuilder.equal(catalogRoot.get("isDiscounted"), isDiscounted);
            }
            return criteriaBuilder.conjunction();
        });
    }
}