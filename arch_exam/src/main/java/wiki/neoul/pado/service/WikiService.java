package wiki.neoul.pado.service;

import lombok.RequiredArgsConstructor;
import wiki.neoul.pado.domain.wiki.Wiki;
import wiki.neoul.pado.domain.wiki.WikiModificationVo;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class WikiService {

    private final WikiServiceRepository repository;

    public String write(WikiWriteRequest request) throws InvalidParameterException {
        request.assertValidation();
        final Wiki wiki = Wiki
                .builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        return repository.save(wiki).getId();
    }

    public String modify(WikiModifyRequest request) throws InvalidParameterException, WikiDocumentNotFoundException {
        request.assertValidation();
        final Wiki wiki = repository
                .find(request.getWikiId())
                .orElseThrow(() -> new WikiDocumentNotFoundException("얘! 위키가 없단다!"));
        wiki.update(request.toValueObject());
        return repository.save(wiki).getId();
    }

    public interface WikiServiceRepository {
        Wiki save(Wiki wiki);

        Optional<Wiki> find(String wikiId);
    }
}
