//
//  PropertyDetailsDescriptionBlock.swift
//  Renting
//
//  Created by Vitaly Khvostov on 30.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct PropertyDetailsDescriptionBlock: View {
    let description: String
    
    @State private var isDescriptionExpanded = false
    @State private var isDescriptionCanBeExpanded = false
        
    var body: some View {
        VStack(alignment: .leading) {
            Text("Overview")
                .font(.headline)
                .fontWeight(.semibold)
            Spacer().frame(height: 12)
            Text(description)
                .font(.system(size: systemFontSize))
                .lineLimit(isDescriptionExpanded ? Int.max : maxLines)
                .background(GeometryReader { geometry in
                    Color.clear.onAppear {
                        self.determineTruncation(geometry)
                    }
                })
            Spacer().frame(height: 4)
            if isDescriptionCanBeExpanded {
                Text(getExpandButtonTitle(isExpanded: isDescriptionExpanded))
                    .foregroundColor(Color.appPrimary)
                    .onTapGesture {
                        isDescriptionExpanded = !isDescriptionExpanded
                    }
            }
        }
    }
    
    private func determineTruncation(_ geometry: GeometryProxy) {
        // Calculate the bounding box we'd need to render the
        // text given the width from the GeometryReader.
        let total = self.description.boundingRect(
            with: CGSize(
                width: geometry.size.width,
                height: .greatestFiniteMagnitude
            ),
            options: .usesLineFragmentOrigin,
            attributes: [.font: UIFont.systemFont(ofSize: systemFontSize)],
            context: nil
        )

        if total.size.height > geometry.size.height {
            self.isDescriptionCanBeExpanded = true
        }
    }
}

private let maxLines = 4
private let systemFontSize: CGFloat = 18

private func getExpandButtonTitle(isExpanded: Bool) -> String {
    if isExpanded {
        return "Read less"
    } else {
        return "Read more..."
    }
}

struct PropertyDetailsDescriptionBlock_Previews: PreviewProvider {
    static var previews: some View {
        PropertyDetailsDescriptionBlock(
            description: ComponentPropertyDetailsPreviews().apartment.description_
        )
    }
}
