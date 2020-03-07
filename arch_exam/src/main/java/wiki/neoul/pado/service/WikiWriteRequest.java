package wiki.neoul.pado.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wiki.neoul.pado.domain.account.Account;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class WikiWriteRequest implements Validatable {

    private String title;
    private String content;
    private Account account;

    @Builder
    public WikiWriteRequest(String title, String content, Account account) {
        this.title = title;
        this.content = content;
        this.account = account;
    }

    @Override
    public void assertValidation() throws InvalidParameterException {
        try {
            Objects.requireNonNull(title);
            Objects.requireNonNull(content);
        } catch (NullPointerException npe) {
            throw new InvalidParameterException("Invalid parameter.");
        }
    }
}
