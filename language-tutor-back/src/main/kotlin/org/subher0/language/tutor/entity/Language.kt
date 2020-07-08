package org.subher0.language.tutor.entity

import org.hibernate.annotations.GeneratorType
import javax.persistence.*

@Table(name = "language")
@Entity
class Language(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "languageSequenceGenerator")
    @SequenceGenerator(name = "languageSequenceGenerator", sequenceName = "language_seq")
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "code")
    var code: String? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "is_active")
    var isActive: Boolean? = null,

    @Column(name = "description")
    var description: String? = null
) {
}