//
//  PrimaryButtonStyle.swift
//  Renting
//
//  Created by Vitaly Khvostov on 03.03.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI

struct PrimaryButtonStyle: ButtonStyle {
    
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .padding()
            .frame(minWidth: 0, maxWidth: .infinity)
            .padding(.horizontal)
            .foregroundColor(Color.white)
            .background(RoundedRectangle(cornerRadius: 8).fill(Color.blue))
    }
}
