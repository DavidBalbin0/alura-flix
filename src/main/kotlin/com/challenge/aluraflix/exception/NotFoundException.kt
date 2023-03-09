package com.challenge.aluraflix.exception

import java.util.function.Supplier

class NotFoundException(message: String?): RuntimeException(message) {
}