package org.subher0.language.tutor.entity

import org.hibernate.annotations.GeneratorType
import javax.persistence.*

@Table(name = "part_of_speech")
@Entity
class PartOfSpeech(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partOfSpeechSequenceGenerator")
    @SequenceGenerator(name = "partOfSpeechSequenceGenerator", sequenceName = "part_of_speech_seq")
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "description")
    var description: String? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "language_id")
    var language: Language? = null
) {
}