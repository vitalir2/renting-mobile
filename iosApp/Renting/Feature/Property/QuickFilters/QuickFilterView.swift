//
//  QuickFilter.swift
//  Renting
//
//  Created by Vitaly Khvostov on 15.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI

struct QuickFilter: View {
    let name: String
    let isApplied: Bool
    let onSelected: () -> Void
    
    private let baseShape = RoundedRectangle(cornerRadius: 16)
    
    var body: some View {
        Text(name)
            .padding(8)
            .foregroundColor(isApplied ? Color.onPrimary : Color.appPrimary)
            .background(
                baseShape
                    .fill(isApplied ? Color.appPrimary : Color.backgroundPrimary)
            )
            .overlay(baseShape
                .stroke(Color.appPrimary, lineWidth: 1)
            )
            .onTapGesture {
                onSelected()
            }
    }
}

struct QuickFilter_Previews: PreviewProvider {
    static var previews: some View {
        QuickFilter(
            name: "Filter1",
            isApplied: false,
            onSelected: {}
        )
        .previewDisplayName("Unselected")
        
        QuickFilter(
            name: "SelectedF",
            isApplied: true,
            onSelected: {}
        )
        .previewDisplayName("Selected")
        
    }
}
