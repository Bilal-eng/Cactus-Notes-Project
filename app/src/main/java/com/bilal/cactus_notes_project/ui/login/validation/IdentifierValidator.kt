package com.bilal.cactus_notes_project.ui.login.validation

import com.bilal.cactus_notes_project.R

class IdentifierValidator : NotEmptyValidator(
    R.string.email_or_username_is_required
) {
}