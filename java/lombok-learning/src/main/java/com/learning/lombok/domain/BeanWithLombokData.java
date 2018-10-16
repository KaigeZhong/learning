package com.learning.lombok.domain;

import lombok.Data;
//等同于@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@Data
public class BeanWithLombokData {
    private Integer id;
    private String name;
    private String age;
    private String gender;
}
