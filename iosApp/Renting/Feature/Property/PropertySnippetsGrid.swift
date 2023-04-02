//
//  PropertySnippetsGrid.swift
//  Renting
//
//  Created by Vitaly Khvostov on 02.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct PropertySnippetsGrid: View {
    let snippets: [PropertySnippet]
    var body: some View {
        ScrollView {
            LazyVGrid(
                columns: [
                    GridItem(),
                    GridItem()
                ]
            ) {
                ForEach(snippets, id: \PropertySnippet.id) { snippet in
                    PropertySnippetCard(snippet: snippet)
                }
            }
        }
    }
}

struct PropertySnippetsGrid_Previews: PreviewProvider {
    static var previews: some View {
        PropertySnippetsGrid(
            snippets: [
                PropertySnippet.preview,
                PropertySnippet.preview,
                PropertySnippet.preview
            ]
        )
    }
}
