//
//  PropertyTypeQuickFilter.swift
//  Renting
//
//  Created by Vitaly Khvostov on 14.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct PropertyTypeQuickFilterView: View {
    let quickFilter: PropertyTypeQuickFilter
    let onSelected: () -> Void
    
    private let baseShape = RoundedRectangle(cornerRadius: 16)
    
    var body: some View {
        QuickFilter(
            name: quickFilter.name,
            isApplied: quickFilter.isApplied,
            onSelected: onSelected
        )
    }
}

private extension PropertyTypeQuickFilter {
    var name: String {
        switch type {
        case PropertyType.familyHouse:
            return "🏡 Family house"
        case PropertyType.apartment:
            return "🏢 Apartment"
        case PropertyType.land:
            return "🏞️ Land"
        default:
            return "🤨 Unknown"
        }
    }
}

struct PropertyTypeQuickFilter_Previews: PreviewProvider {
    static var previews: some View {
        PropertyTypeQuickFilterView(
            quickFilter: PropertyTypeQuickFilter(
                type: PropertyType.apartment,
                isApplied: false
            ),
            onSelected: {}
        )
        .previewDisplayName("Unselected")
        PropertyTypeQuickFilterView(
            quickFilter: PropertyTypeQuickFilter(
                type: PropertyType.familyHouse,
                isApplied: true
            ),
            onSelected: {}
        )
        .previewDisplayName("Selected")
    }
}
