package com.ex1.demo.argument;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import com.ex1.demo.entity.User;

// public class UserArgumentProvider implements ArgumentsProvider {

// @Override
// public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
// throws Exception {
// return
// Stream.of(Arguments.of(User.builder().userName("ram").password("pass1").build()),
// Arguments.of(User.builder().userName("user2").password("pass2").build()));
// }

// }
