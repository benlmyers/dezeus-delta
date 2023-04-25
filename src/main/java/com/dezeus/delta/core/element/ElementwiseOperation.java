package com.dezeus.delta.core.element;

import java.util.List;

public interface ElementwiseOperation {
    Element apply(List<Element> parameters);
}
