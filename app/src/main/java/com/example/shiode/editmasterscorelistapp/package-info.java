@EpoxyDataBindingLayouts({R.layout.score_item, R.layout.progress_bar_item})
@PackageModelViewConfig(rClass = R.class)
@PackageEpoxyConfig(
        requireAbstractModels = true,
        requireHashCode = true,
        implicitlyAddAutoModels = true
)
package com.example.shiode.editmasterscorelistapp;

import com.airbnb.epoxy.EpoxyDataBindingLayouts;
import com.airbnb.epoxy.PackageEpoxyConfig;
import com.airbnb.epoxy.PackageModelViewConfig;
