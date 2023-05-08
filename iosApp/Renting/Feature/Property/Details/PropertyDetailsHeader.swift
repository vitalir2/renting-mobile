//
//  PropertyDetailsHeader.swift
//  Renting
//
//  Created by Vitaly Khvostov on 30.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct PropertyDetailsHeader: View {
    @State private var index = 0
    
    let images: [SharedImage]
    
    var body: some View {
        ZStack {
            if images.isEmpty {
                Image("RentingLogoFull")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
            } else {
                TabView(selection: $index) {
                    ForEach(images, id: \.self.id_) { image in
                        RentingImage(
                            image: image
                        ) { phase in
                            if let image = phase.image {
                                image
                                    .resizable()
                            } else {
                                Image("RentingLogoFull")
                                    .resizable()
                            }
                        }
                    }
                }
                .tabViewStyle(.page(indexDisplayMode: .never))
                .frame(minWidth: 0, maxWidth: .infinity)
                .aspectRatio(1, contentMode: .fit)
            }
        }
    }
}

struct PropertyDetailsHeader_Previews: PreviewProvider {
    static var previews: some View {
        PropertyDetailsHeader(
            images: []
        )
    }
}
