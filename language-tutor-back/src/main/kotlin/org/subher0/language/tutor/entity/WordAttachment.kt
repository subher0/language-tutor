package org.subher0.language.tutor.entity

import org.hibernate.annotations.GeneratorType
import javax.persistence.*

@Table(name = "word_attachment")
@Entity
class WordAttachment(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wordAttachmentSequenceGenerator")
    @SequenceGenerator(name = "wordAttachmentSequenceGenerator", sequenceName = "word_attachment_seq")
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "uuid")
    var uuid: String? = null,

    @Column(name = "attachment_url")
    var attachmentUrl: String? = null,

    @Column(name = "hyperlink")
    var hyperlink: String? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "word_id")
    var word: Word? = null
) {
}