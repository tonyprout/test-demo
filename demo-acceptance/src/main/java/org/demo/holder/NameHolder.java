package org.demo.holder;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class NameHolder extends GenericHolder<String> {
}
