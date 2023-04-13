//
//  SearchInput.swift
//  Renting
//
//  Created by Vitaly Khvostov on 13.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct SearchInput: View {
    let component: SearchInputComponent
    
    @ObservedObject
    private var models: ObservableValue<SearchInputComponentModel>
    
    init(_ component: SearchInputComponent) {
        self.component = component
        self.models = ObservableValue(component.models)
    }
    
    var body: some View {
        let model = models.value
        RentingInput(
            "Search",
            text: .init(
                get: { model.query },
                set: { query in component.onQueryChanged(content: query)}
            ),
            leadingIcon: {
                Image(systemName: "magnifyingglass")
                    .onTapGesture {
                        component.onSearchClicked()
                    }
            },
            trailingIcon: {
                Image("FiltersIcon")
                    .resizable()
                    .frame(width: 24, height: 24)
                    .onTapGesture {
                        component.onFullFiltersClicked()
                    }
            }
        )
    }
}

struct SearchInput_Previews: PreviewProvider {
    static var previews: some View {
        SearchInput(DummySearchInputComponent())
    }
}
