package pl.investtrack.investtrack;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssetsService{
    private final AssetsRepository assetsRepository;

}
