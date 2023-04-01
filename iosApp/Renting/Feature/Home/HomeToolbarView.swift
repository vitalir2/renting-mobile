//
//  HomeToolbarView.swift
//  Renting
//
//  Created by Vitaly Khvostov on 01.04.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import SwiftUI
import shared

struct HomeToolbarView: View {
    let userInfo: UserInfo
    
    var body: some View {
        HStack {
            Image(systemName: "circle")
                .resizable()
                .frame(width: 40, height: 40)
            Spacer()
                .frame(width: 16)
            VStack(alignment: .leading) {
                Text("Hello 👋")
                    .font(.body)
                    .foregroundColor(.textSecondary)
                Text(userInfo.fullName)
                    .font(.subheadline)
                    .fontWeight(.semibold)
            }
            Spacer()
        }
        .padding(16)
    }
}

struct HomeToolbarView_Previews: PreviewProvider {
    static var previews: some View {
        HomeToolbarView(
            userInfo: UserInfo(
                login: "Login",
                firstName: "Kenny",
                lastName: "Jeddy",
                imageUrl: "something"
            )
        )
    }
}
