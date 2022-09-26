package com.example.mensajesespias.infrastructure.network.rest.resource;

import com.example.mensajesespias.application.network.mst.IMinimumCommunicationNetworkSpanningSearcher;
import com.example.mensajesespias.application.network.save.ICommunicationNetworkSaver;
import com.example.mensajesespias.domain.network.CommunicationNetwork;
import com.example.mensajesespias.domain.network.mst.MinimumCommunicationNetworkSpanningSearcherFactory;
import com.example.mensajesespias.infrastructure.network.rest.mapper.CreateCommunicationNetworkMapper;
import com.example.mensajesespias.infrastructure.network.rest.request.CommunicationNetworkRequest;
import com.example.mensajesespias.infrastructure.network.rest.response.CommunicationNetworkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/network")
@RequiredArgsConstructor
public class CommunicationNetworkResource {

  private final ICommunicationNetworkSaver communicationNetworkSaver;
  private final CreateCommunicationNetworkMapper createCommunicationNetworkMapper;

  private final MinimumCommunicationNetworkSpanningSearcherFactory minimumCommunicationNetworkSpanningSearcherFactory;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommunicationNetworkResponse> save(
      @RequestBody CommunicationNetworkRequest communicationNetworkRequest) {
    CommunicationNetwork savedNetwork = communicationNetworkSaver
        .save(createCommunicationNetworkMapper.toDomain(communicationNetworkRequest));

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(createCommunicationNetworkMapper.toResponse(savedNetwork));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<CommunicationNetworkResponse> mst(@PathVariable Long id,
      @RequestParam String algorithm) {
    IMinimumCommunicationNetworkSpanningSearcher searcher =
        minimumCommunicationNetworkSpanningSearcherFactory.getMinimumCommunicationNetworkSpanningSolver(algorithm);

    CommunicationNetwork solvedNetwork = searcher.search(id);

    return ResponseEntity.ok(createCommunicationNetworkMapper.toResponse(solvedNetwork));
  }
}
