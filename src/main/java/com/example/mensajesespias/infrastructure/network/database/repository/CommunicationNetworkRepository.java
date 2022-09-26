package com.example.mensajesespias.infrastructure.network.database.repository;

import com.example.mensajesespias.domain.network.CommunicationNetwork;
import com.example.mensajesespias.domain.network.ICommunicationNetworkRepository;
import com.example.mensajesespias.infrastructure.network.database.entity.CommunicationNetworkEntity;
import com.example.mensajesespias.infrastructure.network.database.mapper.CommunicationNetworkEntityMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunicationNetworkRepository implements ICommunicationNetworkRepository {

  private final ISpringCommunicationNetworkRepository springCommunicationNetworkRepository;
  private final CommunicationNetworkEntityMapper communicationNetworkEntityMapper;

  @Override
  public CommunicationNetwork save(CommunicationNetwork network) {
    CommunicationNetworkEntity savedNetwork = springCommunicationNetworkRepository
        .save(communicationNetworkEntityMapper.toEntity(network));

    return communicationNetworkEntityMapper.toDomain(savedNetwork);
  }

  @Override
  public Optional<CommunicationNetwork> find(Long id) {
    Optional<CommunicationNetworkEntity> communicationEntity = springCommunicationNetworkRepository.findById(id);
    return communicationEntity.map(communicationNetworkEntityMapper::toDomain);
  }
}
