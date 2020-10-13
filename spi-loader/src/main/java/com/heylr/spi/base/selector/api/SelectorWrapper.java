package com.heylr.spi.base.selector.api;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SelectorWrapper {

    private ISelector selector;

    private Class conditionType;

}
