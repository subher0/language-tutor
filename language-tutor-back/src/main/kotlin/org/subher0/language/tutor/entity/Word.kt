package org.subher0.language.tutor.entity

import org.hibernate.annotations.GeneratorType
import java.time.ZonedDateTime
import javax.persistence.*

@Table(name = "word_form")
@Entity
class Word(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wordSequenceGenerator")
    @SequenceGenerator(name = "wordSequenceGenerator", sequenceName = "word_seq")
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "word")
    var word: String? = null,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "create_time")
    var createTime: ZonedDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "language_id")
    var language: Language? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "part_of_speech_id")
    var partOfSpeech: PartOfSpeech? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "word_form_id")
    var wordForm: WordForm? = null,

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
            name = "word_to_form",
            joinColumns = [
                JoinColumn(name = "word_id")
            ],
            inverseJoinColumns = [
                JoinColumn(name = "word_form_id")
            ]
    )
    var otherForms: Set<Word>? = null
) {
}