package com.example.mensajesespias.infrastructure.network.database.repository;

import com.example.mensajesespias.infrastructure.network.database.entity.CommunicationNetworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpringCommunicationNetworkRepository extends JpaRepository<CommunicationNetworkEntity, Long>{

}
