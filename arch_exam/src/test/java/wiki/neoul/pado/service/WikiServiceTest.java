package wiki.neoul.pado.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wiki.neoul.pado.domain.wiki.Wiki;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class WikiServiceTest {

    @Mock
    private WikiService.WikiServiceRepository repository;

    private WikiService wikiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        wikiService = new WikiService(repository);
    }

    @Test
    void 정상적인_요청으로_위키_작성_요청을_수행함() throws Exception {
        // arrange
        final WikiWriteRequest request = WikiWriteRequest
                .builder()
                .title("정상적인 위키 제목")
                .content("불꽃길만 걸으세요")
                .build();
        final Wiki wiki = Wiki
                .builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        // stubbing
        when(repository.save(any(Wiki.class)))
                .thenReturn(wiki);

        // act
        final String wikiId = wikiService.write(request);

        // assert
        assertNotEquals(null, wikiId);
        assertNotEquals("", wikiId);
    }

    @Test
    void 비정상적인_파라미터로_요청을_받은_경우_InvalidParameterException이_발생한다() {

        // arrange
        final WikiWriteRequest request = new WikiWriteRequest();

        Assertions
                .assertThrows(
                        InvalidParameterException.class,    // assert
                        () -> wikiService.write(request)   // act
                );
    }

    @Test
    void 정상적인_파라미터로_수정_요청을_받은_경우_정상_동작() throws InvalidParameterException, WikiDocumentNotFoundException {
        final WikiModifyRequest request = WikiModifyRequest
                .builder()
                .wikiId(UUID.randomUUID().toString())
                .title("title")
                .content("content")
                .build();

        final Wiki wiki = Wiki
                .builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        when(repository.save(any(Wiki.class)))
                .thenReturn(wiki);

        final String wikiId = wikiService.modify(request);

        assertNotEquals(null, wikiId);
        assertNotEquals("", wikiId);
    }

    @Test
    void 비정상적인_파라미터로_수정_요청_시_InvalidParameterException이_발생() throws Exception {
        WikiModifyRequest request = new WikiModifyRequest();

        Assertions
                .assertThrows(
                        InvalidParameterException.class,
                        () -> wikiService.modify(request)
                );
    }

    @Test
    void 위키를_수정_하려는데_해당_위키가_존재하지_않는_경우_WikiDocumentNotFoundException_발생() throws InvalidParameterException {
        final WikiModifyRequest request = WikiModifyRequest
                .builder()
                .wikiId(UUID.randomUUID().toString())
                .title("null")
                .content("null")
                .build();

        // stubbing
        when(repository.find(anyString()))
                .thenReturn(Optional.empty());

        Assertions
                .assertThrows(
                        WikiDocumentNotFoundException.class,
                        () -> wikiService.modify(request)
                );
    }

}