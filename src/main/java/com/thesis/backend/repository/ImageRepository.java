package com.thesis.backend.repository;

import com.thesis.backend.entities.Image;
import com.thesis.backend.entities.Trainer;
import com.thesis.backend.entities.leave.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "SELECT * FROM images WHERE images.name = :imageName", nativeQuery = true)
    Image findImageByName(@Param("imageName") String imageName);

}
