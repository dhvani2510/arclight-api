package com.example.arclight.repositories;

import com.example.arclight.entities.FileVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileVersionRepository extends JpaRepository<FileVersion,Long>
{
}
