//
//  MutableStateBuilder.swift
//  Renting
//
//  Created by Vitaly Khvostov on 26.02.2023.
//  Copyright © 2023 Renting. All rights reserved.
//

import shared

// swiftlint:disable force_cast
func valueOf<T: AnyObject>(_ value: T) -> Value<T> {
    return MutableValueBuilderKt.MutableValue(initialValue: value) as! MutableValue<T>
}
// swiftlint:enable force_cast
