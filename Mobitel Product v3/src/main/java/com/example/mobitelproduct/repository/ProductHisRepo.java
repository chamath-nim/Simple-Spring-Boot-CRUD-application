package com.example.mobitelproduct.repository;

import com.example.mobitelproduct.entity.Product;
import com.example.mobitelproduct.entity.ProductHis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductHisRepo extends JpaRepository<ProductHis, Long> {

    List<ProductHis> findProductHisByPkgName(String name);

}
