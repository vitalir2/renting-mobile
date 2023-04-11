//
//  PropertySnippetCard.swift
//  Renting
//
//  Created by Vitaly Khvostov on 02.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct PropertySnippetCard: View {
    let snippet: PropertySnippet
    
    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            RentingImage(
                image: snippet.image
            ) { phase in
                HStack {
                    Spacer()
                    if let image = phase.image {
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                            .frame(width: .infinity)
                            .clipShape(RoundedRectangle(cornerRadius: 4))
                    } else {
                        Image("RentingLogoFull")
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                    }
                    Spacer()
                }
            }
            Spacer()
                .frame(height: 8)
            Text("\(snippet.price) ₽")
                .font(.title3)
                .fontWeight(.bold)
            Text(snippet.location)
                .font(.subheadline)
                .foregroundColor(Color.textSecondary)
        }
        .padding(16)
        .background(RoundedRectangle(cornerRadius: 16).fill(Color.backgroundPrimary))
    }
}

struct PropertySnippetCard_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            PropertySnippetCard(
                snippet: PropertySnippet.preview
            )
            .frame(width: .infinity, height: 500)
        }
        .frame(width: .infinity, height: .infinity)
        .background(Color.appPrimary)
    }
}
