//
//  PropertyTypeQuickFilters.swift
//  Renting
//
//  Created by Vitaly Khvostov on 15.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct PropertyTypeQuickFiltersView: View {
    let filters: PropertyTypeQuickFilters?
    let onFilterSelected: (PropertyType) -> Void
    let clearSelectedFilters: () -> Void
    
    var body: some View {
        if let filters = filters {
            ScrollView(Axis.Set.horizontal, showsIndicators: false) {
                HStack {
                    QuickFilter(
                        name: "✅ All",
                        isApplied: filters.appliedFilter == nil,
                        onSelected: clearSelectedFilters
                    )
                    ForEach(filters.list, id: \PropertyTypeQuickFilter.type) { filter in
                        PropertyTypeQuickFilterView(
                            quickFilter: filter,
                            onSelected: {
                                onFilterSelected(filter.type)
                            }
                        )
                    }
                }
            }
        } else {
            PropertyTypeQuickFiltersSkeleton()
        }
    }
}

struct PropertyTypeQuickFilters_Previews: PreviewProvider {
    static var previews: some View {
        PropertyTypeQuickFiltersView(
            filters: PropertyTypeQuickFilters(
                list: PropertyTypeQuickFilters.companion.filtersOrder
                    .map { type in
                        PropertyTypeQuickFilter(type: type, isApplied: false)
                    }
            ),
            onFilterSelected: { _ in },
            clearSelectedFilters: {}
        )
    }
}
