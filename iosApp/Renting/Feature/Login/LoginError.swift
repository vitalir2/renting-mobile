//
//  EquitableLoginError.swift
//  Renting
//
//  Created by Vitaly Khvostov on 16.03.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import Foundation
import shared

enum LoginError {
    case invalidLoginOrPassword
    case unknown
    
    static func fromShared(_ loginError: SharedLoginError?) -> LoginError? {
        switch loginError {
        case .none:
            return nil
        case .some(let wrapped):
            if wrapped is SharedLoginErrorInvalidLoginOrPassword {
                return .invalidLoginOrPassword
            } else if wrapped is SharedLoginErrorUnknown {
                return .unknown
            } else {
                return nil
            }
        }
    }
}
