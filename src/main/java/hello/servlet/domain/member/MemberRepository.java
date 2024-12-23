package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

public class MemberRepository {

	private static Map<Long, Member> store = new ConcurrentHashMap<>();
	private static AtomicLong sequence = new AtomicLong(0);
	
	private static final MemberRepository instance = new MemberRepository();
	
	public static MemberRepository getInstance() {
		return instance;
	}
	
	private MemberRepository() {
		
	}
	
	public Member save(Member member) {
		member.setId(sequence.incrementAndGet());
		store.put(member.getId(), member);
		return member;
	}
	
	public Member findById(Long id) {
		return store.get(id);
	}
	
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}
	
}
