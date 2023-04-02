//
//  PropertyDetailsView.swift
//  Renting
//
//  Created by Vitaly Khvostov on 02.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct PropertyDetailsView: View {
    let component: PropertyDetailsComponent
    
    init(_ component: PropertyDetailsComponent) {
        self.component = component
    }
    
    var body: some View {
        Text("Property Details")
    }
}

struct PropertyDetailsView_Previews: PreviewProvider {
    class Stub : PropertyDetailsComponent {
        
    }
    
    static var previews: some View {
        PropertyDetailsView(Stub())
    }
}
