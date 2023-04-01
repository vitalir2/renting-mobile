//
//  RentingImage.swift
//  Renting
//
//  Created by Vitaly Khvostov on 01.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct RentingImage<Content>: View where Content: View {
    let image: SharedImage
    let scale: CGFloat
    let transaction: Transaction
    @ViewBuilder
    let content: (AsyncImagePhase) -> Content
    
    public init(
        image: SharedImage,
        scale: CGFloat = 1,
        transaction: Transaction = Transaction(),
        @ViewBuilder content: @escaping (AsyncImagePhase) -> Content
    ) {
        self.image = image
        self.scale = scale
        self.transaction = transaction
        self.content = content
    }
    
    var body: some View {
        switch image {
        case let imageUrl as SharedImageUrl:
            AsyncImage(
                url: URL(string: imageUrl.fullUrl),
                scale: scale,
                transaction: transaction,
                content: content
            )
        default:
            Text("Something went wrong")
        }
    }
}

struct RentingImage_Previews: PreviewProvider {
    static var previews: some View {
        RentingImage(
            image: SharedImageUrl(path: "image")
        ) {_ in
            Text("Heh")
        }
    }
}
