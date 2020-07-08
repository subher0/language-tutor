package org.subher0.language.tutor.entity

import org.hibernate.annotations.GeneratorType
import javax.persistence.*

@Table(name = "word_view")
@Entity
class WordView(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wordViewSequenceGenerator")
    @SequenceGenerator(name = "wordViewSequenceGenerator", sequenceName = "word_view_seq")
    @Column(name = "id")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "word_id")
    var word: Word? = null
) {
}