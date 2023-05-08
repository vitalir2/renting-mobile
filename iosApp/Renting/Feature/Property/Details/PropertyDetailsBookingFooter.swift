//
//  PropertyDetailsBookingFooter.swift
//  Renting
//
//  Created by Vitaly Khvostov on 30.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI

struct PropertyDetailsBookingFooter: View {
    @Binding var showToast: Bool
    
    var body: some View {
        // TODO: add normal background like in the design
        Button(
            action: {
                showToast = true
            },
            label: {
                Text("Booking Now")
            }
        )
        .buttonStyle(PrimaryButtonStyle())
        .padding(16)
        .background(
            Color.backgroundPrimary.clipShape(RoundedRectangle(cornerRadius: 8))
        )
    }
}

struct PropertyDetailsBookingFooter_Previews: PreviewProvider {
    static var previews: some View {
        PropertyDetailsBookingFooter(
            showToast: .init(get: { false }, set: { _ in })
        )
    }
}
