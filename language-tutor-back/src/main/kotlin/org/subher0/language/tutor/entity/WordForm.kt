package org.subher0.language.tutor.entity

import org.hibernate.annotations.GeneratorType
import javax.persistence.*

@Table(name = "word_form")
@Entity
class WordForm(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wordFormSequenceGenerator")
    @SequenceGenerator(name = "wordFormSequenceGenerator", sequenceName = "word_form_seq")
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "is_root_form")
    var isRootForm: Boolean? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "language_id")
    var language: Language? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "part_of_speech_id")
    var partOfSpeech: PartOfSpeech? = null
) {
}