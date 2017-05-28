package eu.trustdemocracy.ranker.core.interactors;

public interface Interactor<RequestDTO, ResponseDTO> {

  ResponseDTO execute(RequestDTO requestDTO);
}
